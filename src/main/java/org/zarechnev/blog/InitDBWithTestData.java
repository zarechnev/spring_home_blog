package org.zarechnev.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zarechnev.blog.dto.article.ArticleDTO;
import org.zarechnev.blog.dto.article.DTOArticleService;
import org.zarechnev.blog.dto.section.DTOSectionService;
import org.zarechnev.blog.dto.section.SectionDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitDBWithTestData {

    @Autowired
    private DTOArticleService dtoArticleService;

    @Autowired
    private DTOSectionService dtoSectionService;

    private InitDBWithTestData() { }

    @Bean
    public void initDB() {
        List<ArticleDTO> articles = dtoArticleService.findAll();
        if (articles.isEmpty()) {

            ArticleDTO article;

            ArrayList<String> sections = new ArrayList<>();
            sections.add("Психология");
            sections.add("IT");
            sections.add("Разное");
            sections.add("Политика");
            for (String i : sections) {
                dtoSectionService.save(new SectionDTO(i));
            }

            for (int i = 0; i < 3; i++) {
                article = new ArticleDTO("Автор " + i, "Заголовок статьи " + i);
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

                dtoArticleService.save(article);
            }
        }
    }
}
