package org.example.utils

import java.lang.IllegalArgumentException

class WeightedQuickUnion(private var count: Int) {
    private val parent = IntArray(count)
    private val size = IntArray(count)

    fun count() = count

    fun find(index: Int): Int {
        validate(index)
        var root = index
        while (root != parent[root])
            root = parent[root]
        return root
    }

    fun connected(a: Int, b: Int): Boolean {
        return find(a) == find(b)
    }

    fun union(a: Int, b: Int) {
        val rootA = find(a)
        val rootB = find(b)
        if (rootA == rootB) return

        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB
            size[rootB] += size[rootA]
        } else {
            parent[rootB] = rootA
            size[rootA] += size[rootB]
        }
    }

    private fun validate(index: Int) {
        if (index < 0 || index > count())
            throw IllegalArgumentException("Index $index is not between 0 and ${count - 1}")
    }
}

