/**
 * 
 */
package com.leolian.code.fragment.book.concurrence.chapter03;

import java.util.Collection;
import java.util.SortedSet;

/**
 * Description: 栈封闭
 * @author lianliang
 * @date 2018年1月10日 上午10:00:57
 */
public class StackClose {
	
	public int loadTheArk(Collection<Animal> candidates) {
		SortedSet<Animal> animals;
		int numPairs = 0;
		Animal candidate = null;

		/*animals = new TreeSet<Animal>(new SpeciesGenderComparator());
		animals.addAll(candidates);
		for (Animal a : animals) {
			if(candidate == null || !candidate.isPotentialMate(a))
				candidate = a;
			else {
				ark.load(new AnimalPair(candidate, a));
				++numPairs;
				candidate = null;
			}
		}*/
		return numPairs;
	}
	
}

class Animal {
	
}