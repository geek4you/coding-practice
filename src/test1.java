import java.util.LinkedList;
import java.util.Stack;

public class test1<T> {

	class MinIndexes{
		int min1;
		int min2;
		
		public MinIndexes(int min1, int min2){
			this.min1 = min1;
			this.min2 = min2;
		}
	}
	public void findOptimalCost(int[][] costMatrix){
		
		int houses = costMatrix.length;
		int colors = costMatrix[0].length;
		
		int[][] solution = new int[houses][colors];
		
		
		for(int i=0; i<colors;i++){
			solution[houses-1][i] = costMatrix[houses-1][i];
		}
		
		
		for(int i=houses-2; i>=0;i--){										//O(M*N)
			
			MinIndexes minIndexes = findMinimumIndexes(solution, houses-1); //O(m)
			
			int min1Index = minIndexes.min1;
			int min2Index = minIndexes.min2;
			
			
			for(int j=0;j<colors;j++){										//O(m)
				if(j == min1Index)
					solution[i][j] = costMatrix[i][j] + solution[i+1][min2Index];
				else
					solution[i][j] = costMatrix[i][j] + solution[i+1][min1Index];
					
			}
			
		}
		
		int min = findMinimumIndexes(solution, houses-1).min1; //O(m)
		System.out.println("Minimum is: "+ solution[0][min]);
	}
	
	MinIndexes findMinimumIndexes(int[][] arr, int n){
		int min1 = 0, min2 = 0;
		
		for(int i=1; i < arr[0].length - 1; i++ ){
			if(arr[n][min1] > arr[n][i])
				min1 = i;
		}
		
		for(int i=1; i < arr[0].length - 1; i++ ){
			if(arr[n][min2] > arr[n][i] && arr[n][i] != arr[n][min1])
				min2 = i;
		}
		
		return new MinIndexes(min1, min2);
		
	}
	
	
	/* Driver program to check above functions */
	public static void main(String args[])
	{
		int[][] cost = {{3,1,5,6,2},
				{4,1,2,2,4},
				{4,2,1,5,1},
				{3,3,1,2,4},
				{1,2,5,1,3}
		};
		
		test1 t = new test1();
		
		t.findOptimalCost(cost);
		
	}
}