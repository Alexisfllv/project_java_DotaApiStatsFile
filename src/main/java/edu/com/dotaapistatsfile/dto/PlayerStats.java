package edu.com.dotaapistatsfile.dto;

public record PlayerStats(
        String heroName,
        int kills,
        int deaths,
        int assists
) {}
