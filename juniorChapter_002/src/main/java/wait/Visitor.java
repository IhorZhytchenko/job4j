package wait;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.BlockingQueue;
/**
 * Visitor.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 03.05.2018
 */
public class Visitor extends SimpleFileVisitor<Path> {
    private List<String> exts;
    private BlockingQueue<Path> files;

    public Visitor(List<String> exts, BlockingQueue<Path> files) {
        this.exts = exts;
        this.files = files;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        for (String ext: this.exts) {
            if (path.toString().endsWith(ext)) {
                this.files.offer(path);
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
