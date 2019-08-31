package org.zarechnev.blog.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HTMLRenderFromMarkDownServiceImpl implements HTMLRenderFromMarkDownService {

    Parser parser;
    HtmlRenderer renderer;

    @PostConstruct
    void init() {
        parser = Parser.builder().build();
        renderer = HtmlRenderer.builder().build();
    }

    @Override
    public String getHTML(String markDownText) {
        Node document = parser.parse(markDownText);
        return renderer.render(document);
    }

}
