//Main Function
fun main(args: Array<String>) {
    //Problem # 1
    println("PROBLEM # 1")
    println("Contains Duplicate: ${containsDuplicate(intArrayOf(1, 2, 3, 1))}")
    println("Contains Duplicate Optimal: ${containsDuplicateOptimal(intArrayOf(1, 2, 3, 1))}")
    //Problem # 2
    println("PROBLEM # 2")
    println("Find Single Number: ${singleNumber(intArrayOf(4, 1, 2, 1, 2))}")
    println("Find Single Number: ${singleNumberOptimal(intArrayOf(4, 1, 2, 1, 2))}")
}


/*
* Problem # 1
* Given an integer array nums, return true if any value appears at least twice in the array, and return false if every
* element is distinct.
* */
fun containsDuplicate(nums: IntArray): Boolean {
    val hashSetNoDups = hashSetOf<Int>()
    for (element in nums) {
        hashSetNoDups.add(element)
    }
    return hashSetNoDups.size != nums.size
}

fun containsDuplicateOptimal(nums: IntArray): Boolean {
    val hashSetNoDups: HashSet<Int> = HashSet()
    for (i in nums.indices) {
        if (hashSetNoDups.contains(nums[i])) {
            return true
        } else {
            hashSetNoDups.add(nums[i])
        }
    }
    return false
}

/*
* Problem # 2
* Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
* Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
* */
fun singleNumber(nums: IntArray): Int {
    val hashSetNums = hashSetOf<Int>()
    val hashMapNums = hashMapOf<Int, Boolean>()
    for (i in nums.indices) {
        hashMapNums[nums[i]] = hashSetNums.add(nums[i])
    }
    return hashMapNums.filter { it.value }.keys.first()
}

fun singleNumberOptimal(nums: IntArray): Int {
    var res = 0
    for (num in nums) {
        res = res xor num
    }
    return res
}
