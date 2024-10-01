package com.mirahtec.apisiraparents.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParserString {
    public static String parserFileName(String link) {
        String[] parts = link.split("/");
        //recuperer le dernier element de la liste
        return parts[parts.length - 1];
    }

    public static String parseTelephone(String telephone) throws IllegalArgumentException {
        // Supprime tous les espaces et le signe +
        telephone = telephone.replaceAll("[\\s+]+", "");

        // Vérifie si le numéro de téléphone contient uniquement des chiffres
        if (telephone.matches("\\d+")) {
            // Ajoute le préfixe 221 si le numéro ne commence pas par 221
            if (!telephone.startsWith("221")) {
                telephone = "221" + telephone;
            }
            return "+"+telephone;
        } else {
            throw new IllegalArgumentException("Le numéro de téléphone doit contenir uniquement des chiffres");
        }
    }

    public class UriParser {
        public static String parseUri(String link) {
            if (link.contains("..")) {
                link = link.replace("..", "");
            }
            return link;
        }
    }
}
