/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise5Test extends PetDomainForKata
{
    @Test
    public void partitionPetAndNonPetPeople()
    {
        PartitionMutableList<Person> partitionMutableList = people.partition(Person::isPetPerson);
        Verify.assertSize(7, partitionMutableList.getSelected());
        Verify.assertSize(1, partitionMutableList.getRejected());
    }

    @Test
    public void getOldestPet()
    {
        Pet oldestPet = people.flatCollect(Person::getPets).maxBy(p -> p.getAge());
        Assert.assertEquals(PetType.DOG, oldestPet.getType());
        Assert.assertEquals(4, oldestPet.getAge());
    }

    @Test
    public void getAveragePetAge()
    {
        double averagePetAge = people.flatCollect(Person::getPets).collectInt(Pet::getAge).average();
        Assert.assertEquals(1.8888888888888888, averagePetAge, 0.00001);
    }

    @Test
    public void addPetAgesToExistingSet()
    {
        // Hint: Use petAges as a target collection
        MutableIntSet petAges = IntSets.mutable.with(5);
        people.flatCollect(Person::getPets).collectInt(Pet::getAge, petAges);
        Assert.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), petAges);
    }

    @Test
    public void refactorToEclipseCollections()
    {
        // Replace List and ArrayList with Eclipse Collections types
        MutableList<Person> people = Lists.mutable.with(
                new Person("Mary", "Smith")
                    .addPet(PetType.CAT, "Tabby", 2),
                new Person("Bob", "Smith")
                    .addPet(PetType.CAT, "Dolly", 3)
                    .addPet(PetType.DOG, "Spot", 2),
                new Person("Ted", "Smith")
                    .addPet(PetType.DOG, "Spike", 4),
                new Person("Jake", "Snake")
                    .addPet(PetType.SNAKE, "Serpy", 1),
                new Person("Barry", "Bird")
                    .addPet(PetType.BIRD, "Tweety", 2),
                new Person("Terry", "Turtle")
                    .addPet(PetType.TURTLE, "Speedy", 1),
                new Person("Harry", "Hamster")
                    .addPet(PetType.HAMSTER, "Fuzzy", 1)
                    .addPet(PetType.HAMSTER, "Wuzzy", 1),
                new Person("John", "Doe"));

        // Replace Set and HashSet with Eclipse Collections types
        MutableIntSet petAges = people.flatCollect(Person::getPets).collectInt(Pet::getAge).toSet();

        //extra bonus - convert to a primitive collection
        Assert.assertEquals(IntSets.mutable.with(1, 2, 3, 4), petAges);
    }
}
