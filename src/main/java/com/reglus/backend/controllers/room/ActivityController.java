package com.reglus.backend.controllers.room;

import com.reglus.backend.model.entities.rooms.Activity;
import com.reglus.backend.model.entities.rooms.ActivityRequest;
import com.reglus.backend.model.entities.rooms.Room;
import com.reglus.backend.model.entities.users.Educator;
import com.reglus.backend.repositories.room.ActivityRepository;
import com.reglus.backend.repositories.room.RoomRepository;
import com.reglus.backend.repositories.users.EducatorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    private final ActivityRepository activityRepository;
    private final RoomRepository roomRepository;
    private final EducatorRepository educatorRepository;

    public ActivityController(ActivityRepository activityRepository, RoomRepository roomRepository, EducatorRepository educatorRepository) {
        this.activityRepository = activityRepository;
        this.roomRepository = roomRepository;
        this.educatorRepository = educatorRepository;
    }

    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody ActivityRequest request) {
        try {
            Room room = roomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + request.getRoomId()));

            Educator educator = educatorRepository.findById(request.getEducatorId())
                    .orElseThrow(() -> new RuntimeException("Educator not found with ID: " + request.getEducatorId()));

            Activity activity = new Activity();
            activity.setRoom(room);
            activity.setEducator(educator);
            activity.setTitle(request.getTitle());
            activity.setMaxPoints(request.getMaxPoints());
            activity.setDataLimit(request.getDataLimit());
            activity.setCreatedAt(LocalDateTime.now());
            activity.setUpdatedAt(LocalDateTime.now());

            Activity savedActivity = activityRepository.save(activity);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/room/{roomId}/activities")
    public ResponseEntity<?> getActivitiesByRoomId(@PathVariable Long roomId) {
        try {
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

            List<Activity> activities = activityRepository.findByRoom(room);

            return ResponseEntity.ok(activities);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<?> getActivityById(@PathVariable Long activityId) {
        try {
            Activity activity = activityRepository.findById(activityId)
                    .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + activityId));

            return ResponseEntity.ok(activity);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
