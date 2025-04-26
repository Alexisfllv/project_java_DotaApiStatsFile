package edu.com.dotaapistatsfile.service;

import edu.com.dotaapistatsfile.dto.PlayerStats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    public String toCSV(List<PlayerStats> statsList) {
        StringBuilder builder = new StringBuilder();
        builder.append("Hero,Kills,Deaths,Assists\n");

        for (PlayerStats stats : statsList) {
            builder.append("%s,%d,%d,%d\n".formatted(
                    stats.heroName(), stats.kills(), stats.deaths(), stats.assists()));
        }

        return builder.toString();
    }
}
