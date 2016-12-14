package com.baldrichcorp.algo.ds

import scala.annotation.tailrec
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

/**
  * Trait that represents a Trie. There is no removal operation b/c it is kind of a pain in the ass
  * to implement and there aren't too many applications for Tries where one must remove words.
  */
sealed trait Trie extends Traversable[String] {
  /**
    * Adds a new word to the Trie.
    *
    * @param word the word to add.
    * @return this.
    */
  def append(word: String): this.type // returns itself to allow for more fluent operation
  /**
    * Finds all words in the Trie that contain the given string as prefix.
    *
    * @param prefix The prefix that must be used to search for words in the Trie.
    * @return A <em>Seq</em> with all words in the Trie that contain the given string as prefix.
    */
  def findByPrefix(prefix: String): Seq[String]

  /**
    * Check whether a given word exists in the Trie. Not particularly useful given that we already have
    * a method to find words by prefix.
    *
    * @param word the word to search for.
    * @return <em>true</em> if <em>word</em> exists in the trie. <em>false</em> otherwise.
    */
  def contains(word: String): Boolean
}

/**
  * A TrieNode is a Trie in the same way a node in a tree is also a tree. Each node may have a character and an optional
  * word which when set indicates that this particular node ends a word that has been explicitly added to the Trie.
  * it represents.
  *
  * @param char The char this node contains. If <em>None</em>, this node is probably the root of the Trie.
  * @param word The word that is completed by the character of this node.
  */
class TrieNode(val char: Option[Char] = None, var word: Option[String] = None) extends Trie {
  val children: scala.collection.mutable.Map[Char, TrieNode] =
    new java.util.TreeMap[Char, TrieNode]()

  /**
    * @inheritdoc
    */
  override def append(key: String) = {
    @tailrec def f(node: TrieNode, index: Int): Unit = {
      if (index == key.length) {
        node.word = Some(key)
      }
      else {
        val currentChar = key.charAt(index)
        f(node.children.getOrElseUpdate(currentChar, new TrieNode(Some(currentChar))), index + 1)
      }
    }
    f(this, 0)
    this
  }

  /**
    * @inheritdoc
    */
  override def findByPrefix(prefix: String): Seq[String] = {
    def f(node: TrieNode, index: Int, buffer: ListBuffer[String]): ListBuffer[String] = {
      if (index == prefix.length) {
        buffer ++ node
      } else {
        node.children.get(prefix.charAt(index)) match {
          case Some(child) => f(child, index + 1, buffer)
          case None => buffer
        }
      }
    }
    f(this, 0, new ListBuffer[String]())
  }

  /**
    * @inheritdoc
    */
  override def contains(word: String): Boolean = {
    @tailrec def f(node: TrieNode, index: Int): Boolean = {
      if (index == word.length)
        node.word.isDefined
      else {
        node.children.get(word.charAt(index)) match {
          case Some(child) => f(child, index + 1)
          case None => false
        }
      }
    }
    f(this, 0)
  }

  /**
    * @inheritdoc
    */
  override def foreach[U](fun: (String) => U): Unit = {
    @tailrec def f(nodes: TrieNode*): Unit = {
      if (nodes.nonEmpty) {
        nodes.foreach(n => n.word.foreach(fun))
        f(nodes.flatMap(n => n.children.values): _*)
      }
    }
    f(this)
  }
}