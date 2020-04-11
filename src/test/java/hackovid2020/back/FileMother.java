package hackovid2020.back;

import hackovid2020.back.dao.File;
import hackovid2020.back.dao.support.FileType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FileMother {
    public static File createRandomFile() {
        return File.createFile(
                "file",
                FileType.JPG,
                "https://picsum.photos/200",
                null,
                null,
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime()
        );
    }

    public static List<File> createRandomFiles(int numberOfFilesToCreate) {
        List<File> files = new ArrayList<>();
        for (int i = 0; i < numberOfFilesToCreate; i++) {
            files.add(createRandomFile());
        }
        return files;
    }
}
