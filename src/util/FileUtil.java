package main.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;

public class FileUtil {
    public static class FileWrapper {
        private Path path;
        private String s_path;
        private File file;
        public boolean created;

        public FileWrapper(Path path, File file, boolean created) throws IOException {
            this.path = path;
            this.s_path = path.toString();
            this.file = file;
            this.created = created;
        }

        public String read() throws IOException {
            List<String> lines = Files.readAllLines(Path.of(getPath()));
            String out = "";
            for (ListIterator<String> it = lines.listIterator(); it.hasNext();) {
                String line = it.next();
                out += line+"\n";
            }
            return out;
        }

        public void write(String text) throws IOException {
            Files.write(Path.of(getPath()), text.getBytes());
        }
        public void append(String text) throws IOException {
            String original = this.read();
            Files.write(Path.of(getPath()), (original+text).getBytes());
        }

        public String getPath() {
            return file.getAbsolutePath();
        }

        public void delete() throws IOException { file.delete(); }
    }
    public static FileWrapper open(String path) throws IOException {
        File f = new File(path);
        boolean created = !f.exists();
        f.createNewFile();
        return new FileWrapper(Path.of(path), f, created);
    }
    public static FileWrapper open(String path, String defaultContent) throws IOException {
        File f = new File(path);
        boolean created = !f.exists();
        if (created) {
            Files.write(Path.of(path), defaultContent.getBytes());
        }
        f.createNewFile();
        return new FileWrapper(Path.of(path), f, created);
    }

    public static FileWrapper open(Path path) throws IOException {
        File f = new File(path.toString());
        boolean created = !f.exists();
        f.createNewFile();
        return new FileWrapper(path, f, created);
    }
    public static FileWrapper open(Path path, String defaultContent) throws IOException {
        File f = new File(path.toString());
        boolean created = !f.exists();
        if (created) {
            Files.write(path, defaultContent.getBytes());
        }
        f.createNewFile();
        return new FileWrapper(path, f, created);
    }
}
