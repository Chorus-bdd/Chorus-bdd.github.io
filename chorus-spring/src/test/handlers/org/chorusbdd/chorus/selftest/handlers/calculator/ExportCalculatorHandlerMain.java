package org.chorusbdd.chorus.selftest.handlers.calculator;

import org.chorusbdd.chorus.remoting.jmx.ChorusHandlerJmxExporter;

/**
 * Used to run a simple process that can be used to test Chorus features
 *
 * Created by: Steve Neal
 * Date: 14/11/11
 */
public class ExportCalculatorHandlerMain {

    public static void main(String[] args) throws Exception {
        new ChorusHandlerJmxExporter(new CalculatorHandler()).export();
        Thread.sleep(1000 * 60 * 5); //keeps process alive for 5 mins
    }

}
