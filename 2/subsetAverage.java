import mpi.*;
import java.util.*;

public class subsetAverage
{
	public static void main(String args[]) throws Exception
	{
		MPI.Init(args);
		
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		
		int block_size = 3;
		int root = 0;
		
		int[] send_buffer = new int [size * block_size];
		double[] recv_double_buffer = new double [size];
		
		// Initialize a random number generator
		Random ran = new Random();
		
		if (rank == root)
		{
			// Generating an array of random numbers
			for(int i=0; i<(size * block_size); i++)
			{
				send_buffer[i] = ran.nextInt(100);
			}
			// Printing array of random numbers
			System.out.println("The randomly generated numbers are:\n");
			for(int i=0; i<(size * block_size); i++)
			{
				System.out.println(send_buffer[i]+" ");
			}
		}
		
		int[] recv_buffer = new int [block_size];
		
		MPI.COMM_WORLD.Scatter(send_buffer, 0, block_size, MPI.INT, recv_buffer, 0, block_size, MPI.INT, root);
		
		System.out.println("Process with Rank #" + rank + " has data:\n");
		double average = 0;
		double[] temp_avg_buffer = new double[1];
		
		for(int i=0; i < recv_buffer.length; i++)
		{
			System.out.println(recv_buffer[i]+" ");
			average += recv_buffer[i];
		}
		
		temp_avg_buffer[0] = average / recv_buffer.length;
		
		MPI.COMM_WORLD.Gather(temp_avg_buffer, 0, 1, MPI.DOUBLE, recv_double_buffer, 0, 1, MPI.DOUBLE, root);
		// [-, -, -, -] recv_double_buffer -> avg
		
		// int averages = new int [size];
		
		double final_avg = 0;
		
		if (rank == root)
		{
			for(int i=0; i<recv_double_buffer.length; i++)
			{
				final_avg += recv_double_buffer[i];
			}
			final_avg = final_avg / recv_double_buffer.length;
			System.out.println("Average of all Subsets: " + final_avg);
		}
		
		MPI.Finalize();	
	}
}
