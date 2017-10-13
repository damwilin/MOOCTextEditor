package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author Damian Wiliński
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;


    public AutoCompleteDictionaryTrie() {
        root = new TrieNode();
    }


    /**
     * Insert a word into the trie.
     * For the basic part of the assignment (part 2), you should convert the
     * string to all lower case before you insert it.
     * <p>
     * This method adds a word by creating and linking the necessary trie nodes
     * into the trie, as described outlined in the videos for this week. It
     * should appropriately use existing nodes in the trie, only creating new
     * nodes when necessary. E.g. If the word "no" is already in the trie,
     * then adding the word "now" would add only one additional node
     * (for the 'w').
     *
     * @return true if the word was successfully added or false if it already exists
     * in the dictionary.
     */
    public boolean addWord(String word) {
        word = word.toLowerCase();
        if (word.isEmpty())
            return false;

        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Set available = trieNode.getValidNextCharacters();
            if (!available.contains(c)) {
                trieNode.insert(c);
                if (i == word.length() - 1) {
                    trieNode.getChild(c).setEndsWord(true);
                    size++;
                    return true;
                }
            }
            trieNode = trieNode.getChild(c);
            if (trieNode.getText().equals(word) && !trieNode.endsWord()) {
                trieNode.setEndsWord(true);
                size++;
                return true;
            }
        }
        return false;
    }

    /**
     * Return the number of words in the dictionary.  This is NOT necessarily the same
     * as the number of TrieNodes in the trie.
     */
    public int size() {
        return size;
    }


    /**
     * Returns whether the string is a word in the trie, using the algorithm
     * described in the videos for this week.
     */
    @Override
    public boolean isWord(String s) {
        s = s.toLowerCase();
        if (root == null || s.isEmpty())
            return false;

        TrieNode currNode = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (currNode.getChild(c) != null) {
                currNode = currNode.getChild(c);
            }
            if (i == s.length() - 1 && currNode.getText().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions
     * of the prefix string. All legal completions must be valid words in the
     * dictionary. If the prefix itself is a valid word, it is included
     * in the list of returned words.
     * <p>
     * The list of completions must contain
     * all of the shortest completions, but when there are ties, it may break
     * them in any order. For example, if there the prefix string is "ste" and
     * only the words "step", "stem", "stew", "steer" and "steep" are in the
     * dictionary, when the user asks for 4 completions, the list must include
     * "step", "stem" and "stew", but may include either the word
     * "steer" or "steep".
     * <p>
     * If this string prefix is not in the trie, it returns an empty list.
     *
     * @param prefix         The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        //    Create a list of completions to return (initially empty)
        List<String> completionsList = new ArrayList<String>();
        if (numCompletions == 0)
            return completionsList;
        // This method should implement the following algorithm:
        // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
        //    empty list
        TrieNode stemNode = findNode(prefix);
        if (stemNode == null)
            return completionsList;
        // 2. Once the stem is found, perform a breadth first search to generate completions
        //    using the following algorithm:
        //    Create a queue (LinkedList) and add the node that completes the stem to the back
        //       of the list.
        List<TrieNode> queue = new LinkedList();
        queue.add(stemNode);
        //    While the queue is not empty and you don't have enough completions:
        while (!queue.isEmpty()) {
            //       remove the first Node from the queue
            TrieNode currRemove = queue.remove(0);
            //       If it is a word, add it to the completions list
            if (currRemove.endsWord())
                completionsList.add(currRemove.getText());

            if (completionsList.size() == numCompletions)
                break;
            Set<Character> validChars = currRemove.getValidNextCharacters();
            //       Add all of its child nodes to the back of the queue
            for (Character c : validChars)
                queue.add(currRemove.getChild(c));
        }
        // Return the list of completions
        return completionsList;
    }

    private TrieNode findNode(String word) {
        if (word.isEmpty())
            return root;
        TrieNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Set validChars = currNode.getValidNextCharacters();
            if (validChars.contains(c)) {
                currNode = currNode.getChild(c);
                if (i == word.length() - 1)
                    return currNode;
            }
        }
        return null;
    }

    // For debugging

    public void printTree() {
        printNode(root);
    }

    /**
     * Do a pre-order traversal from this node down
     */
    public void printNode(TrieNode curr) {
        if (curr == null)
            return;

        System.out.println(curr.getText());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }


}