package org.chorusbdd.chorus.remoting.jmx;

import org.chorusbdd.chorus.selftest.handlers.calculator.CalculatorHandler;

/**
 * Created by: Steve Neal
 * Date: 14/10/11
 */
public class RunChorusJmxFeatureExporter {
    public static void main(String[] args) throws Exception {
        new ChorusHandlerJmxExporter(new CalculatorHandler()).export();
        Thread.sleep(1000 * 60 * 30); //30 minutes
    }
}
