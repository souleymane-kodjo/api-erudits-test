package com.mirahtec.apisiraparents.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParserString {
    public static String parserFileName(String link) {
        log.info("Parsing file name from link: {}", link);
        //transformer en list de string suivqnt le "/"
        String[] parts = link.split("/");
        //recuperer le dernier element de la liste
        String fileName = parts[parts.length - 1];
        log.info("File name parsed: {}", fileName);
        return fileName;
    }
}
