package control;

import org.junit.Test;

import static org.junit.Assert.*;

public class SwitchTest {

    @Test
    public void show() throws InterruptedException {
        Switch sw = new Switch();
        sw.switcher();
        Thread.sleep(1000);
        sw.stopSwitcher();
        System.out.println(sw.getString());

    }

}