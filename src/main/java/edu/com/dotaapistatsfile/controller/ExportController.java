package edu.com.dotaapistatsfile.controller;

import edu.com.dotaapistatsfile.dto.PlayerStats;
import edu.com.dotaapistatsfile.service.ExportService;
import edu.com.dotaapistatsfile.service.PlayerStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportController {

    private final PlayerStatsService statsService;
    private final ExportService exportService;

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlayerStats> exportAsJson() {
        return statsService.getSampleStats();
    }

    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<Resource> exportAsCsv() {
        String csvData = exportService.toCSV(statsService.getSampleStats());
        ByteArrayResource resource = new ByteArrayResource(csvData.getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=player_stats.csv")
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body((Resource) resource);
    }
}
