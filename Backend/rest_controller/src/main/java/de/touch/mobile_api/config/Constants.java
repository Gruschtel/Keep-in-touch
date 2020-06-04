package de.touch.mobile_api.config;

import java.util.regex.Pattern;

public interface Constants {

    // ======================================
    // STATIC VARS
    // ======================================
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final Pattern EMAIL_REGEX = Pattern.compile(".*@.*\\.\\w+");
    public static final Pattern ZIPCODE_REGEX = Pattern.compile("[0-9]{5}");
    public static final Pattern PHONENUMBER_REGEX = Pattern.compile("0[0-9]+");

    public static final String PLACEHOLDER_IMAGE = "/images/placeholder.jpg";

    // ======================================
    // JWT Token
    // ======================================

    // Time where a token is valid. In milliseconds
    // 1 Day
    // TODO: chance token validity on production
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24;

    //
    public static final String JWT_TOKEN_AUTHORIZATION_VALUE = "TOUCH ";
    public static final String JWT_TOKEN_AUTHORIZATION_KEY = "Authorization";

    // ======================================
    // ARRAYS
    // ======================================
    /**
     * Passwort pattern
     */
    public static final Pattern[] PASSWORD_REGEX = { Pattern.compile("^(?=.*[a-z]).+$"), // At least one lower char
            Pattern.compile("^(?=.*[A-Z]).+$"), // " one upper char
            Pattern.compile("^(?=.*[0-9]).+$") // " one number
    };

    // ======================================
    // ENUMS
    // ======================================

    public interface EnumConstant {
        public String getDescription();
        public char getDatabaseValue();
    }

    public enum Gender {
        Male("Male", 'm'), Female("Female", 'w'), None("None", 'n');

        private String description;
        private char databaseValue;

        private Gender(String description, char databaseValue) {
            this.description = description;
            this.databaseValue = databaseValue;
        }

        public String getDescription() {
            return this.description;
        }

        public char getDatabaseValue() {
            return this.databaseValue;
        }
    }

    public enum Role {
        Admin("Admin", 'a'), User("User", 'u');

        private String description;
        private char databaseValue;

        private Role(String description, char databaseValue) {
            this.description = description;
            this.databaseValue = databaseValue;
        }

        public String getDescription() {
            return this.description;
        }

        public char getDatabaseValue() {
            return this.databaseValue;
        }

        public boolean equals(Role other) {
            if (description != other.description)
                return false;
            if (databaseValue != other.databaseValue)
                return false;
            return true;
        }
    }

    public enum AccountStatus {
        Deactivated("Deactivated", 'd'), Deleted("Deleted", 'r'), Blocked("Blocked", 'b'), Activated("Activated", 'a'),
        Verified("Verified", 'v');

        private String description;
        private char databaseValue;

        private AccountStatus(String description, char databaseValue) {
            this.description = description;
            this.databaseValue = databaseValue;
        }

        public String getDescription() {
            return this.description;
        }

        public char getDatabaseValue() {
            return this.databaseValue;
        }

        public boolean equals(Role other) {
            if (description != other.description)
                return false;
            if (databaseValue != other.databaseValue)
                return false;
            return true;
        }
    }
}