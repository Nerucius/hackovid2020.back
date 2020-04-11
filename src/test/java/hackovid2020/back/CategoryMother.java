package hackovid2020.back;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.support.CategoriesEnum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CategoryMother {
    public static Category createCarniceriaCategory() {
        return Category.createCategory(
                CategoriesEnum.CARNICERIA,
                null,
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime()
        );
    }

    public static List<Category> createRandomCategories(int numberOfCategoriesToCreate) {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < numberOfCategoriesToCreate; i++) {
            categories.add(createCarniceriaCategory());
        }
        return categories;
    }
}
