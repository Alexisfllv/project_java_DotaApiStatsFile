package edu.com.dotaapistatsfile.service;

import edu.com.dotaapistatsfile.dto.PlayerStats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsService {
    public List<PlayerStats> getSampleStats() {
        return List.of(
                new PlayerStats("Pudge", 10, 2, 8),
                new PlayerStats("Invoker", 7, 3, 12)
        );
    }
}
