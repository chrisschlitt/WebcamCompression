import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;

public class DifferencingLibrary {
	
	public static Diff getDiff(byte[] a, byte[] b){
		HashMap<Integer, Byte> diff = new HashMap<Integer, Byte>();
		int i = 0;
		int j = 0;
		int smallerLength = Math.max(a.length, b.length);
		int smaller = 1;
		int length = b.length;
		if(a.length > b.length){
			smaller = 0;
		}
		while(j < smallerLength){
			if(a[i] == b[j]){
				i++;
				j++;
				continue;
			} else {
				diff.put(j, b[j]);
				i++;
				j++;
			}
		}
		if(smaller == 0){
			while(j < length){
				diff.put(j, b[j]);
			}
		}
		Diff result = new Diff(diff, length);
		return result;
		
	}
	
	public static byte[] rebuild(Diff diff, byte[] a){
		byte[] b = new byte[diff.length];
		int i = 0;
		while(i < diff.length){
			if(diff.diff.containsKey(i)){
				b[i] = diff.diff.get(i);
			} else {
				b[i] = a[i];
			}
			i++;
		}
		
		return b;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[] a = "CHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLE1".getBytes();
		byte[] b = "CHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLECHRISISREALLYINCREDIBLE2".getBytes();
		System.out.println("Getting difference");
		Diff diff = DifferencingLibrary.getDiff(a, b);
		System.out.println("Rebuilding");
		
		byte[] c = DifferencingLibrary.rebuild(diff, a);
		boolean same = Arrays.equals(b, c);
		System.out.println("The result is rebuilt successfully: " + same);
		
		
		
		System.out.println("Index Size: " + diff.diff.size());
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(diff.diff);
        oos.close();
        System.out.println("Data Size: " + baos.size());
        System.out.println("As opposed to: " + b.length);
		
		
	}

}