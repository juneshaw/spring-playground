package com.endpoints;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class TextController {

    @GetMapping("/camelize")
    public String camelize(
            @RequestParam String original,
            @RequestParam(required = false) boolean initialCap) {
        return TextService.camelize(original, initialCap);
    }

    @GetMapping("/redact")
    public String redact(
            @RequestParam MultiValueMap<String, String> params) {
        List<String> originals;
        List<String> badWords;
        String original;
        String redacted = "";

        originals = params.get("original");
        if (originals.size() == 1) {
            original = originals.get(0);
            badWords = params.get("badWord");
            Iterator<String> badWordIter = badWords.iterator();

            redacted = new String(original);
            while (badWordIter.hasNext()) {
                redacted = TextService.redact(
                        redacted,
                        badWordIter.next());
            }
        }
        return redacted;
    }

    @PostMapping(value = "/encode", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String encode(
            @RequestParam Map<String, String> params) {
        String key;
        String encoded = "";
        String message;

        key = params.get("key");
        message = params.get("message");
        if (!key.isEmpty() &&
                (key.length() == 26) &&
                (!message.isEmpty())) {
            encoded = TextService.encodeMessage(message, key);
        }
        return encoded;
    }

    @PostMapping(value = "/s/{a}/{b}")
    public String replaceAll(
            @PathVariable String a,
            @PathVariable String b,
            @RequestBody String original) throws Exception {
        return TextService.replaceAll(original, a, b);
    }
}
