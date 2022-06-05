package insertionsort

fun main() {
    // 무작위의 카드 더미 (IntArray) 를 만든다.
    val cards = arrayOf(1, 8, 3, 7, 6, 5, 4)
    // 왼손(카드를 담아둘 IntArray) 를 만든다.
    val leftHand = LeftHand(storage = mutableListOf())

    for (i in 0 until cards.lastIndex) {
        leftHand.store(cards[i])
    }

    println(leftHand.storage)
}

class LeftHand(
    val storage: MutableList<Int>
) {
    fun store(card: Int) {
        // 1. 왼손에 카드가 있는지 확인한다.
        if (storage.isEmpty()) {
            storage.add(card)
            return
        }
        // 2. 있다면 왼손에 카드를 넣는다.
        storage.add(card)
        // 3. 가장 오른쪽 부터 -> 왼쪽까지 하나씩 수를 꺼낸다.
        for (i in searchPointIndex() downTo 0) {
            // 삽입하려는 카드가 꺼낸 카드보다 크다면 Loop 를 탈출한다.
            if (storage[i] isNotSwap card) {
                return
            }
            // 삽입하려는 카드와 꺼낸 카드보다 작다면 위치를 바꾼다.
            if (storage[i] isSwap card) {
                swap(i, card)
            }
        }
    }

    private fun swap(i: Int, card: Int) {
        val tmp: Int = storage[i]
        storage[i] = card
        storage[i + 1] = tmp
    }

    private fun searchPointIndex(): Int {
        return storage.lastIndex - 1
    }

    private infix fun Int.isSwap(card: Int): Boolean {
        return this > card
    }

    private infix fun Int.isNotSwap(card: Int): Boolean {
        return this <= card
    }
}
