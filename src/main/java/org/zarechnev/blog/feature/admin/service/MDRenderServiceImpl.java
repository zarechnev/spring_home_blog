package org.zarechnev.blog.feature.admin.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MDRenderServiceImpl implements MDRenderService {

    private Parser parser = Parser.builder().build();
    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    @Override
    public String getHTML(String markDownText) {
        Node document = parser.parse(markDownText);
        return renderer.render(document);
    }

}
