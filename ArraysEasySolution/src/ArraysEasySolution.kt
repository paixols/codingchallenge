//Main Function
fun main(args: Array<String>) {
    //Problem # 1
    println("Contains Duplicate: ${containsDuplicate(intArrayOf(1,2,3,1))} \n")
    println("Contains Duplicate Optimal: ${containsDuplicateOptimal(intArrayOf(1,2,3,1))} \n")
}


/*
* Problem # 1
* Given an integer array nums, return true if any value appears at least twice in the array, and return false if every
* element is distinct.
* */
fun containsDuplicate(nums: IntArray): Boolean {
    val hashSetNoDups = hashSetOf<Int>()
    for (element in nums){ hashSetNoDups.add(element) }
    return hashSetNoDups.size != nums.size
}

fun containsDuplicateOptimal(nums: IntArray): Boolean {
    val hashSetNoDups: HashSet<Int> = HashSet()
    for (i in nums.indices) {
        if (hashSetNoDups.contains(nums[i])) {
            return true
        }
        else {
            hashSetNoDups.add(nums[i])
        }
    }
    return false
}


