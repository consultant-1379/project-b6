package com.ericsson.tgf.kyzen.controller;

import com.ericsson.tgf.kyzen.entity.TeamDAO;
import com.ericsson.tgf.kyzen.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("metrics")
public class MetricsController {

    @Autowired
    private MetricsService metricsService;

    @GetMapping("/team/{id}")
    public ResponseEntity<TeamDAO> getTeamMetrics(@PathVariable Long id){
        TeamDAO responseBody = metricsService.getAllDetailsForTeamById(id);


        if (responseBody == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(responseBody);

    }
}
