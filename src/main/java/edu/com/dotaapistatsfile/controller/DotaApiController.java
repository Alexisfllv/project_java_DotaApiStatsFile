package edu.com.dotaapistatsfile.controller;

import edu.com.dotaapistatsfile.dto.PlayerTotal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class DotaApiController {

    private final RestTemplate restTemplate;

    @GetMapping("/export/csv")
    public ResponseEntity<ByteArrayResource> exportAsCsv() {
        String url = "https://api.opendota.com/api/players/{playerId}/totals";
        String playerId = "321580662";

        PlayerTotal[] totals = restTemplate.getForObject(url, PlayerTotal[].class, playerId);

        StringBuilder csv = new StringBuilder("Field,Games,Total\n");
        for (PlayerTotal t : totals) {
            csv.append(t.field()).append(",")
                    .append(t.n()).append(",")
                    .append(t.sum()).append("\n");
        }

        ByteArrayResource resource = new ByteArrayResource(csv.toString().getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=player_stats.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}

