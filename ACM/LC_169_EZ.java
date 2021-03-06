/*
* @Author:  zihaowang
* @Email:   zihao.wang1@ucdconnect.ie
* @Website: www.wangzihao.org
* @Date:    2020-09-02 20:21:25
* @Last Modified by:   zihaowang
* @Last Modified time: 2020-09-03 10:07:52
*/

// * Boyer Moore majority voting algorithm
// O(n) time, O(1) space
class Solution {
    public int majorityElement(int[] nums) {
        int majorElement = nums[0];
        int count = 0;
        
        for(int i = 0; i < nums.length; i++) {
            if(count == 0) majorElement = nums[i];
            if(nums[i] == majorElement) count++;
            else count--;
        }
        
        // need a double check if the major element is not
        // guaranteed to be shown in the array.
        
        return majorElement;
    }
}


// * Recursion
class Solution {
    public int findMajorityRec(int[] nums, int left, int right) {
		if(left == right)
        	return nums[left];
		
		int mid = left + ((right - left) >> 1);
		
        int majorLeft = findMajorityRec(nums, left, mid);
        int majorRight = findMajorityRec(nums, mid + 1, right);
        
        if(majorLeft == majorRight) 
        	return majorLeft;
        
        // count left and right respectively to find which num occurs more times
        int counterLeft = 0;
        int counterRight = 0;
        for(int i = left; i <= right; i++) {
        	if(nums[i] == majorLeft) counterLeft++;
        	else if(nums[i] == majorRight) counterRight++;
        }
        
        return counterLeft > counterRight ? majorLeft : majorRight;
	}
	public int majorityElement(int[] nums) {
		return findMajorityRec(nums, 0, nums.length - 1);
    }	
}

// HashTable
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hashmap = new HashMap<> ();
        for(int i = 0; i < nums.length; i++) 
        	hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0) + 1);
        
        for(int i = 0; i < nums.length; i++) {
        	if(hashmap.getOrDefault(nums[i], 0) > (nums.length >> 1)) 
        		return nums[i];
        }
        
        return -1;
    }
}

