package org.drulabs.rx;

import org.drulabs.rx.demo.*;

/**
 * Created by kaushald on 25/11/17.
 */
public class RxReRun {

    public static void main(String[] args){
        // Executable exec = new BufferDemo();
        Executable exec = new ErrorDemo();
        // Executable exec = new HotObservableDemo();
        // Executable exec = new MapFilterReduceDemo();
        // Executable exec = new ScanDemo();
        // Executable exec = new TakeDemo();
        // Executable exec = new TakeLastDemo();
        // Executable exec = new SkipDemo();
        // Executable exec = new ZipDemo();

        exec.prepare();
        exec.execute();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

}
