/*
 * Author: Joseph Carter
 * Email: jtcarter5@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Programming Assignment 07
 * Citations: none 
 */ 

/**
 * Represents a single commit in a version control system.
 * Implemented as a simplified model that tracks only which files were changed.
 * Commits are immutable; merges require creating a *new* commit.
 * @author Max Phillips
 * @author Joseph Carter
 */

import java.util.ArrayList;

public class Commit {
  // Auto-generated commit ID (short-hash style, e.g., a1cb2f).
  private String id;

  // User-provided commit message (description of changes).
  private String message;

  // List of (String) filenames that were changed in this commit.
  private ArrayList<String> changedFiles;

  /**
   * Creates a new Commit with the specified message and change list.
   * @param message a String description of the changes in this Commit.
   * @param changedFiles the list of files changed in this Commit.
   */
  public Commit(String message, ArrayList<String> changedFiles) {
    this.id = HashGenerator.generateShortHash(message); // keep this line as is
    this.message = message;
    this.changedFiles = new ArrayList<>(changedFiles);
  }

  /**
   * Gets id 
   * @return commit id
   */
  public String getId() {
    return this.id;
  }

  /**
   * Gets message
   * @return commit message 
   */ 
  public String getMessage() {
    return this.message;
  }

  /**
   * Gets changedFiles
   * @return defensive copy 
   */
  public ArrayList<String> getChangedFiles() {
    return new ArrayList<>(this.changedFiles);
  }

  // PROVIDED METHOD
  /**
   * Convert this commit to a String representation.
   * @return a String representing this Commit
   */
  @Override
  public String toString() {
    return "" + this.id + " '" + this.message + "' : " + this.changedFiles;
  }
}
