package org.chorusbdd.chorus.handlers.processes;

import org.chorusbdd.chorus.util.logging.ChorusLog;
import org.chorusbdd.chorus.util.logging.ChorusLogFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User: nick
 * Date: 23/07/13
 * Time: 20:52
 * 
 * A class which performs read ahead buffering for Chorus' child process output streams, to a limit of
 * readAheadLimit bytes
 * 
 * When we want to process the output, the buffer is reset to the previous mark
 * 
 * This should solve a potential problem where child processes may attempt to write more to their output streams than 
 * the OS can buffer, which may lead to the child process hanging in an unusual manner if nothing is reading that output.
 * 
 * In this case, if the readAheadLimit is set to a sufficiently high value, Chorus will be able to buffer the 
 * excess output internally, allowing the child process to proceed as it would do under normal circumstances
 * When chorus is ready to process the output it will reset its internal buffer to the previous mark.
 */
class ReadAheadBufferedStream extends BufferedInputStream {

    private static ChorusLog log = ChorusLogFactory.getLog(ReadAheadBufferedStream.class);

    private int readAheadLimit;
    private ReadAheadRunnable readAheadRunnable = new ReadAheadRunnable();
    private Thread readAheadThread = new Thread(readAheadRunnable);

    public ReadAheadBufferedStream(InputStream in, int readAheadLimit) {
        super(in);
        this.readAheadLimit = readAheadLimit;
        mark(readAheadLimit);
    }

    public ReadAheadBufferedStream(InputStream in, int bufferSize, int readAheadLimit) {
        super(in, bufferSize);
        this.readAheadLimit = readAheadLimit;
        mark(readAheadLimit);
    }
    
    public ReadAheadBufferedStream startReadAhead() {
        if ( ! readAheadThread.isAlive()) {
            log.trace("Starting read ahead for " + in);
            readAheadThread.start();
        }
        return this;
    }
    
    public void stopReadAhead() {
        if ( readAheadThread.isAlive()) {
            log.trace("Stopping read ahead for " + in);
            readAheadRunnable.setStopping(true);
            readAheadThread.interrupt();
        }
    }

    public synchronized int read(byte b[], int off, int len) throws IOException {
        reset();
        int result = doRead(b, off, len);
        mark(readAheadLimit);
        return result;
    }

    public synchronized int read() throws IOException {
        reset();
        int result = super.read();
        mark(readAheadLimit);
        return result;
    }

    public synchronized long skip(long n) throws IOException {
        reset();
        long result = super.skip(n);
        mark(readAheadLimit);
        return result;
    }

    public synchronized void mark(int readlimit) {
        readAheadRunnable.clearBytesRead();
        super.mark(readlimit);
    }

    public synchronized int available() throws IOException {
        return markpos < pos ? pos - markpos : super.available();
    }
    
    int getReadAheadBytesRead() {
        return readAheadRunnable.getBytesRead();    
    }

    //shared by the read ahead thread and the read(byte[], int, int) method
    //doesn't reset or take lock
    private int doRead(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    private class ReadAheadRunnable implements Runnable {

        private volatile int bytesRead;
        private volatile boolean stopping;

        public void run() {
            log.trace("Starting read ahead thread for input stream " + in);
            Thread.currentThread().setName("ReadAhead " + in);
            byte[] readAheadBuffer = new byte[1024];
            bytesRead = 0;
            mark(readAheadLimit);
            try {
                while (!stopping) {
                    boolean sleep = true;
                    synchronized (ReadAheadBufferedStream.this) {
                        int shouldRead = (readAheadLimit - bytesRead);
                        if ( shouldRead > readAheadBuffer.length) {
                            readAheadBuffer = new byte[shouldRead];
                        }
                        
                        if ( shouldRead > 0) {
                            int avail = ReadAheadBufferedStream.super.available();
                            if ( avail > 0 )  {
                                bytesRead += doRead(readAheadBuffer, 0, Math.min(avail, shouldRead));
                                sleep = false;
                            }
                        }
                    } 
                    
                    //need to sleep if there were no bytes available or will be a busy loop
                    //do this while no longer holding the lock
                    if (sleep) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            } catch (IOException e) {
                log.trace("Terminated read ahead for process input", e);
            }

            log.trace("Stopped read ahead for " + in);
        }

        private void setStopping(boolean stopping) {
            this.stopping = stopping;
        }

        private void clearBytesRead() {
            bytesRead = 0;
        }

        private int getBytesRead() {
            return bytesRead;
        }
    }
}
