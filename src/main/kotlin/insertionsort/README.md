# INSERTION-SORT

삽입 정렬은 배열의 크기가 작을 수록 유리한 알고리즘 이며 단순하다. 책에 카드 예시가 하나 이해하기 쉽게 나오는데 같이 한번 봐보도록 하자.

> 책상에 카드더미들이 놓여져 있고, 왼손으로 하나씩 집어서 옮긴다고 해보자. 카드 더미들을 옮긴 뒤에 하나씩 비교하며 Ascending 방식으로 정렬을 해야 한다.
> 삽입 정렬의 경우는 가장 오른쪽 부터 하나하나 씩 확인하여 적당한 위치에 옮기면 된다.

책에서 나오는 **INSERTION-SORT 알고리즘의 수도 코드**는 아래와 같다.

```python
A = [5, 2, 4, 6, 1, 3] // Index Starting from 1 not zero

for j = 2 to A.length 
    key = A[j]
    
    i = j - 1
    
    while i > 0 && A[i] > key
        A[i + 1] = A[i]
        i = i - 1
    
    A[i + 1] = key
```

만약 우리가 카드를 j 회 뽑았다면 A[1..j - 1] 의 경우는 이미 손에 정렬된채로 쥐어져 있을 것이다. 따라서 A[j + 1 .. N] 회는 아직 탁자위에 놓였지만 올라올 수 있는 카드들의 개수이다.
따라서 카드를 한장 뽑아서 넣을때마다 Comparison 하는 Loop 안에서 **A[1..j - 1]** 는 변하지 않는다. 이를 **루프 불변성**이라고 한다.

## Loop-Invariant

Loop-Invariant 를 만족하기 위해서 아래와 같이 세가지 조건이 필요하다

- **초기조건**: Loop가 첫번째 반복을 시작하기 전에 Loop-Invariant 가 참이여야 한다.
- **유지조건**: Loop 의 반복이 시작되기 전에 Loop-Invariant 가 참이였다면 다음 Loop 가 시작되기 전에도 참이여야 한다.
- **종료조건**: 루프가 종료될 때 그 불변식이 알고리즘의 타당성을 보이는데 도움이 될 유용한 특성을 지녀야 한다.

일단 첫번째와 두번째는 둘째치고 세번째가 정말 중요한데, 결국 우리가 Insertion Sort 에서 보여줬듯이 왼손에 정렬되어 있는 카드는 우리의 알고리즘을 증명할 수 있기 때문이다.

## My Kotlin Code

```kotlin
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
```