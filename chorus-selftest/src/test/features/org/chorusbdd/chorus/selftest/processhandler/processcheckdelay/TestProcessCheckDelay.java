/**
 *  Copyright (C) 2000-2012 The Software Conservancy and Original Authors.
 *  All rights reserved.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to
 *  deal in the Software without restriction, including without limitation the
 *  rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 *  sell copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 *  IN THE SOFTWARE.
 *
 *  Nothing in this notice shall be deemed to grant any rights to trademarks,
 *  copyrights, patents, trade secrets or any other intellectual property of the
 *  licensor or any contributor except as expressly stated herein. No patent
 *  license is granted separate from the Software, for code that you delete from
 *  the Software, or for combinations of the Software with other software or
 *  hardware.
 */
package org.chorusbdd.chorus.selftest.processhandler.processcheckdelay;

import org.chorusbdd.chorus.handlers.util.JavaVersion;
import org.chorusbdd.chorus.selftest.AbstractInterpreterTest;
import org.chorusbdd.chorus.selftest.ChorusSelfTestResults;
import org.chorusbdd.chorus.selftest.DefaultTestProperties;

/**
 * Created with IntelliJ IDEA.
 * User: nick
 * Date: 25/06/12
 * Time: 22:14
 */
public class TestProcessCheckDelay extends AbstractInterpreterTest {

    final String featurePath = "src/test/features/org/chorusbdd/chorus/selftest/processhandler/processcheckdelay/processcheckdelay.feature";

    final int expectedExitCode = 1;  //fail

    protected int getExpectedExitCode() {
        return expectedExitCode;
    }

    protected String getFeaturePath() {
        return featurePath;
    }

    protected void processActualResults(ChorusSelfTestResults actualResults) {
        
        //in the Jdk15Process we don't always capture the exception
        //since it starts and dies before we can attach to the standard out
        //ProcessBuilderProcess binds to the output atomically on startup
        if ( 
            ! isInProcess() && JavaVersion.IS_1_7_OR_GREATER && 
               !(actualResults.getStandardError().contains("NoClassDefFoundError") ||
               actualResults.getStandardError().contains("Could not find or load main class ThisClassDoesNotExist"))
        ) {
            fail("Expected standard error to contain NoClassDefFoundError");
        }
        
        if ( ! JavaVersion.IS_1_7_OR_GREATER) {
            actualResults.setStandardOutput(
                actualResults.getStandardOutput().replaceAll(
                    "ProcessBuilderProcess", "Jdk15Process"
                )
            );    
        }
        
        actualResults.setStandardError(   //eliminate troublesome stack elements
                "java.lang.NoClassDefFoundError: ThisClassDoesNotExist\n" +
                "Caused by: java.lang.ClassNotFoundException: ThisClassDoesNotExist"
        );
    }
    
    protected void processExpectedResults(ChorusSelfTestResults expectedResults) {
        if ( ! JavaVersion.IS_1_7_OR_GREATER) {
            expectedResults.setStandardOutput(
                    expectedResults.getStandardOutput().replaceAll(
                        "ProcessBuilderProcess", "Jdk15Process"
                    )
            );    
        }
    }
    
}
