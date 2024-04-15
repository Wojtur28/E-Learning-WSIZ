package org.example.elearningcourses.lesson;

import lombok.AllArgsConstructor;
import org.example.elearningsecurity.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private boolean verifyToken(String token) {
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtService.isTokenValid(token, userDetails);
    }

    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable UUID lessonId, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return lessonService.getLessonById(lessonId);
    }

    @GetMapping
    public List<Lesson> getLessons(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return lessonService.getLessons();
    }

    @PostMapping
    public Lesson saveLesson(@RequestBody Lesson lesson, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return lessonService.saveLesson(lesson);
    }

    @PutMapping("/{lessonId}")
    public Lesson updateLesson(@PathVariable UUID lessonId, @RequestBody Lesson lesson, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return lessonService.updateLesson(lessonId, lesson);
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable UUID lessonId, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        lessonService.deleteLesson(lessonId);
    }
}
