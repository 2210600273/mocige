/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : mocige

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 15/05/2025 10:34:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for affixes
-- ----------------------------
DROP TABLE IF EXISTS `affixes`;
CREATE TABLE `affixes`  (
  `AffixID` int NOT NULL AUTO_INCREMENT,
  `Affix` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Definition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`AffixID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for antonyms
-- ----------------------------
DROP TABLE IF EXISTS `antonyms`;
CREATE TABLE `antonyms`  (
  `AntonymID` int NOT NULL AUTO_INCREMENT,
  `WordID` int NULL DEFAULT NULL,
  `RelatedWord` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AntonymID`) USING BTREE,
  INDEX `WordID`(`WordID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for appsettings
-- ----------------------------
DROP TABLE IF EXISTS `appsettings`;
CREATE TABLE `appsettings`  (
  `SettingID` int NOT NULL AUTO_INCREMENT,
  `ReminderTime` time NULL DEFAULT NULL,
  `Theme` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SettingID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `FeedbackID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `Comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FeedbackID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for learningprogress
-- ----------------------------
DROP TABLE IF EXISTS `learningprogress`;
CREATE TABLE `learningprogress`  (
  `ProgressID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `WordID` int NULL DEFAULT NULL,
  `LastReviewed` datetime NULL DEFAULT NULL,
  `ReviewsCount` int NULL DEFAULT 0,
  `MasteryLevel` int NULL DEFAULT NULL,
  PRIMARY KEY (`ProgressID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE,
  INDEX `WordID`(`WordID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for media
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media`  (
  `MediaID` int NOT NULL AUTO_INCREMENT,
  `WordID` int NULL DEFAULT NULL,
  `MediaType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MediaURL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`MediaID`) USING BTREE,
  INDEX `WordID`(`WordID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roots
-- ----------------------------
DROP TABLE IF EXISTS `roots`;
CREATE TABLE `roots`  (
  `RootID` int NOT NULL AUTO_INCREMENT,
  `Root` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Definition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`RootID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sentences
-- ----------------------------
DROP TABLE IF EXISTS `sentences`;
CREATE TABLE `sentences`  (
  `SentenceID` int NOT NULL AUTO_INCREMENT,
  `Sentence` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `Context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`SentenceID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for statistics
-- ----------------------------
DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics`  (
  `StatID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `TotalStudyTime` int NULL DEFAULT NULL,
  `TotalWordsLearned` int NULL DEFAULT NULL,
  PRIMARY KEY (`StatID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for synonyms
-- ----------------------------
DROP TABLE IF EXISTS `synonyms`;
CREATE TABLE `synonyms`  (
  `SynonymID` int NOT NULL AUTO_INCREMENT,
  `WordID` int NULL DEFAULT NULL,
  `RelatedWord` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SynonymID`) USING BTREE,
  INDEX `WordID`(`WordID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for testrecords
-- ----------------------------
DROP TABLE IF EXISTS `testrecords`;
CREATE TABLE `testrecords`  (
  `TestRecordID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `TestDate` datetime NULL DEFAULT NULL,
  `Score` int NULL DEFAULT NULL,
  PRIMARY KEY (`TestRecordID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userpreferences
-- ----------------------------
DROP TABLE IF EXISTS `userpreferences`;
CREATE TABLE `userpreferences`  (
  `PreferenceID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NULL DEFAULT NULL,
  `LearningMode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DailyWordCount` int NULL DEFAULT NULL,
  PRIMARY KEY (`PreferenceID`) USING BTREE,
  INDEX `UserID`(`UserID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PasswordHash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for words
-- ----------------------------
DROP TABLE IF EXISTS `words`;
CREATE TABLE `words`  (
  `WordID` int NOT NULL AUTO_INCREMENT,
  `Word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Pronunciation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PartOfSpeech` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Definition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`WordID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wordsentences
-- ----------------------------
DROP TABLE IF EXISTS `wordsentences`;
CREATE TABLE `wordsentences`  (
  `WordID` int NOT NULL,
  `SentenceID` int NOT NULL,
  PRIMARY KEY (`WordID`, `SentenceID`) USING BTREE,
  INDEX `SentenceID`(`SentenceID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
