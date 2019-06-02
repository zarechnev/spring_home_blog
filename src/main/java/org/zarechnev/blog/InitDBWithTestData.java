package org.zarechnev.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zarechnev.blog.entity.ArticleModel;
import org.zarechnev.blog.entity.SectionModel;
import org.zarechnev.blog.repository.SectionRepository;
import org.zarechnev.blog.repository.ArticleRepository;
import java.util.ArrayList;

/**
 * Component for init DataBase with test data.
 */
@Component
public class InitDBWithTestData {
    @Autowired
    private ArticleRepository articleRepo;
    @Autowired
    private SectionRepository sectionRepo;

    private InitDBWithTestData() { }

    /**
     * Init.
     */
    @Bean
    public void initDB() {
        ArrayList<ArticleModel> articles = (ArrayList<ArticleModel>) articleRepo.findAll();
        if (articles.isEmpty()) {

            ArticleModel article;
            ArrayList<String> section;

            section = new ArrayList<>();
            section.add("Психология");
            section.add("IT");
            section.add("Разное");
            section.add("Политика");
            for (String i : section) {
                this.sectionRepo.save(new SectionModel(i));
            }

            for (int i = 0; i < 3; i++) {
                article = new ArticleModel("Автор " + i, "Заголовок статьи " + i);
                article.setArticleBody("Вышел в релиз выпуск дистрибутива Deepin 15.9, основанного на пакетной базе Debian, " +
                        "но со своим собственным рабочим окружением Deepin Desktop Environment.\n\n" +
                        "    Улучшено управление с сенсорных экранов\n" + "Добавлены жесты для управления с сенсорных экранов" +
                        " (такие как клик по касанию, вызов контекстного меню по удержанию, прокрутка и т.д.)\n" +
                        "    Добавлена экранная клавиатура\n Добавлена опция «Smart Mirror Switch» для выбора самого близкого" +
                        " зеркала для увеличения скорости загрузки пакетов\n Улучшен интерфейс настроек питания, переработаны" +
                        " опции перехода в спящий режим и отключения монитора\n" +
                        "    В параметрах системы добавлена проверка стойкости пароля\n" +
                        "    Оптимизированы эффекты при изменении размера панели\n" +
                        "    В файловом менеджере оптимизировано фоновое переименование файлов, добавлен предпросмотр " +
                        "GIF-изображений, улучшено монтирование накопителей и улучшен интерфейс "
                );

                this.articleRepo.save(article);
            }
        }
    }
}
