package jdbc;

import jdbc.entity.Entries;
import jdbc.entity.Entry;

import java.util.Scanner;
/**
 * class Cli.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 17.05.2018
 */
public class Cli {
    private static final String PATH_XML1 = "juniorChapter_003/src/main/java/jdbc/1.xml";
    private static final String PATH_XML2 = "juniorChapter_003/src/main/java/jdbc/2.xml";
    private final Parser parser;
    private final JdbcManager manager;
    private final Scanner scanner;
    private int n;

    public Cli() {
        this.parser = new Parser();
        this.manager = new JdbcManager();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        this.init();
        long startTime = System.currentTimeMillis();
        this.manager.connect();
        this.manager.addFields(this.n);
        Entries entries = new Entries();
        entries.setEntries(this.manager.getAllFields());
        this.manager.close();
        this.parser.objectToXml(PATH_XML1, entries);
        this.parser.transformFoXslt(PATH_XML1, PATH_XML2);
        entries = this.parser.XmlToObject(PATH_XML2);
        System.out.println(summ(entries));
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Time - " + (time / 1000) + " seconds");
    }

    private long summ(Entries entries) {
        long result = 0L;
        for (Entry entry: entries.getEntries()) {
            result += entry.getField();
        }
        return result;
    }

    private void init() {
        System.out.println("Enter login");
        this.manager.setLogin(this.scanner.nextLine());
        System.out.println("Enter password");
        this.manager.setPassword(this.scanner.nextLine());
        System.out.println("Enter number N");
        this.setN(this.scanner.nextInt());
    }

    public void setN(int n) {
        this.n = n;
    }
}
