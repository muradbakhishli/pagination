package az.ingress.controller;

import az.ingress.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cache")
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @PostMapping
    public void save(@RequestParam Long id) {
        cacheService.save(id);
    }

    @GetMapping
    public void get(@RequestParam String cacheKey) {
        cacheService.get(cacheKey);
    }
}
