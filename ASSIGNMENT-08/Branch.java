// TODO: File Header

/**
 * Represents a branch of commits in a version control system.
 * Contains a stack of commits that have been pushed to the remote repository,
 * and a queue of commits that have been made but not yet pushed.
 */
public class Branch implements Iterable<Commit> {
  // User-provided branch name
  private String name;

  // Queue of commits that have been made but not yet pushed
  private CommitQueue commitQueue;

  // Stack of commits that have been pushed to the remote repository,
  private CommitStack commitStack;

  /**
   * Creates a new Branch with the specified name.
   * This constructor also allows cloning a branch.
   * @param name a String description of this branch; may be null or blank (we aren't picky)
   * @param branch another branch to clone history from/to branch off of; if this is null,
   *        creates a new, empty commitStack and does not throw an exception
   */
  public Branch(String name, Branch branch) {
    // TODO: Instantiate fields
  }

  /**
   * Creates a new Branch with the specified name.
   * @param name a String description of this branch; may be null or blank (we aren't picky)
   */
  public Branch(String name) {
    this(name, null);
  }

  /**
   * Retrieve the name of this Branch.
   * @return the String name of the branch
   */
  public String getName() {
    // TODO: Implement
  }

  /**
   * Retrieves the Commit at the head of this Branch.
   * @return the HEAD commit
   */
  public Commit getHead() {
    // TODO: Implement
  }

  /**
   * Add a new commit to this branch's local/internal commit queue.
   * @param commit the commit to enqueue.
   */
  public void makeCommit(Commit commit) {
    // TODO: Implement
  }

  /**
   * Push all commits from commitQueue to commitStack.
   */
  public void pushAllCommits() {
    // TODO: Implement
  }

  /**
   * Determines whether the branch has any commits.
   * @return true only if the branch is empty
   */
  public boolean isEmpty() {
    // TODO: Implement
  }

  /**
   * Retrieves the number of commits that have been pushed to this Branch.
   * Does not include commits in the queue.
   * @return the size of the commit stack
   */
  public int getNumPushedCommits() {
    // TODO: Implement
  }

  /**
   * Finds a commit whose ID matches the provided ID.
   * @param id the Commit ID to search for.
   * @return the Commit matching the provided ID, if one is found.
   * @throws CommitNotFoundException if no commit matches the provided ID.
   */
  public Commit findCommitById(String id) throws CommitNotFoundException {
    // TODO: Implement
    // TODO: Throw CommitNotFoundException if no matching commits are found
  }

  /**
   * Finds a commit whose message contains the provided String.
   * @param message the substring to search for among all Commit messages.
   * @return the Commit matching the provided message, if one is found.
   * @throws CommitNotFoundException if no commit message matches the provided one.
   */
  public Commit findCommitByMessage(String message) throws CommitNotFoundException {
    // TODO: Implement
    // TODO: Throw CommitNotFoundException if no matching commits are found
  }

  /**
   * Undoes the previous commit pushed to this branch and returns it.
   * @return the last commit pushed to the branch
   */
  public Commit undoLastCommit() throws CommitNotFoundException {
    // TODO: Throw CommitNotFoundException if there are no commits
    // TODO: Implement
  }

  /**
   * Creates and returns an iterator over this branch's *pushed* commits
   *
   * @return a new LinkedIterator over this branch's commit stack
   */
  public LinkedIterator<Commit> iterator() {
    // TODO: Implement
  }

  /**
   * Create a CommitStack consisting of the subset of this Branch's history
   * that was pushed after the specified commit (not including that commit).
   * @param commit the commit to start after. if `null`, retrieve *all* commits in this Branch.
   * @throws CommitNotFoundException if the specified (non-null) commit is not found
   * @return a new CommitStack containing every commit pushed after the specified commit,
   *         with the most recent commit on top
   */
  protected CommitStack getAllCommitsAfter(Commit commit) throws CommitNotFoundException {
    // TODO: Implement
    // TODO: Throw CommitNotFoundException if no matching commits are found
  }

  // PROVIDED METHOD
  /**
   * Create a Branch consisting of the subset of this Branch's history
   * that was pushed after the specified commit (not including that commit).
   * @param name the name of the new branch
   * @param commit the commit to start after. if `null`, the new branch contains *all* commits in this Branch.
   * @throws CommitNotFoundException if the specified (non-null) commit is not found
   * @return a new Branch containing every commit pushed after the specified commit,
   *         with the same head as this Branch
   */
  public Branch createSubBranch(String name, Commit commit) throws CommitNotFoundException {
    Branch branch = new Branch(name);
    branch.commitStack = this.getAllCommitsAfter(commit);
    return branch;
  }

  // PROVIDED METHOD
  /**
   * Print all commits pushed to this Branch's commit stack in either chronological or
   * reverse chronological order, depending on the passed parameter.
   * @param inChronologicalOrder true to print in chronological order, false to print in reverse
   */
  public void printPushedCommits(boolean inChronologicalOrder) {
    if (inChronologicalOrder) {
      this.commitStack.printAllCommitsOldestFirst();
    } else {
      this.commitStack.printAllCommitsNewestFirst();
    }
  }

  // PROVIDED METHOD
  /**
   * Convert this Branch to a string representation.
   * @return a String representing this Branch
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Branch ").append(this.name);
    for (Commit commit : this.commitStack) {
      sb.append("  ").append(commit);
    }
    return sb.toString();
  }
}
