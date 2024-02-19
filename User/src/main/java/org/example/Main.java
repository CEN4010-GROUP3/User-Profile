package org.example;

import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

class UserProfileApp {

    static Map<String, UserProfile> userProfiles = new HashMap<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        // Endpoint for creating a user profile
        app.post("/user", ctx -> {
            UserProfile userProfile = ctx.bodyAsClass(UserProfile.class);
            userProfiles.put(userProfile.getUsername(), userProfile);
            ctx.status(201).result("User profile created successfully");
        });

        // Endpoint for retrieving a user profile
        app.get("/user/{username}", ctx -> {
            String username = ctx.pathParam("username");
            UserProfile userProfile = userProfiles.get(username);
            if (userProfile != null) {
                ctx.json(userProfile);
            } else {
                ctx.status(404).result("User profile not found");
            }
        });

        // Endpoint for updating a user profile
        app.put("/user/{username}", ctx -> {
            String username = ctx.pathParam("username");
            UserProfile userProfile = userProfiles.get(username);
            if (userProfile != null) {
                UserProfile updatedProfile = ctx.bodyAsClass(UserProfile.class);
                userProfile.setName(updatedProfile.getName());
                userProfile.setAddress(updatedProfile.getAddress());
                ctx.status(204).result("User profile updated successfully");
            } else {
                ctx.status(404).result("User profile not found");
            }
        });


    }

    static class UserProfile {
        private String username;
        private String password;
        private String name;
        private String email;
        private String address;
        // Add more fields as needed

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
