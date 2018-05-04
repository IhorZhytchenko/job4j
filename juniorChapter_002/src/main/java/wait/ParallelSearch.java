package wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ParallelSearch.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 03.05.2018
 */
@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;

    private volatile boolean finish = false;

    private BlockingQueue<Path> files = new ArrayBlockingQueue<>(500);

    private CopyOnWriteArrayList<String> paths = new CopyOnWriteArrayList<>();


    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
               Visitor visitor = new Visitor(ParallelSearch.this.exts, ParallelSearch.this.files);
                try {
                    Files.walkFileTree(Paths.get(ParallelSearch.this.root), visitor);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    ParallelSearch.this.finish = true;
                }
            }
        };

        Thread read = new Thread() {
            @Override
            public void run() {
                while (!ParallelSearch.this.finish) {
                    while (ParallelSearch.this.files.size() > 0) {
                        Path path = ParallelSearch.this.files.poll();
                        try {
                            String text = new String(Files.readAllBytes(path));
                            if (text.contains(ParallelSearch.this.text)) {
                                ParallelSearch.this.paths.add(path.toString());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        search.start();
        read.start();
        try {
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     public CopyOnWriteArrayList<String>  result() {
        this.init();
        return this.paths;
    }


}
