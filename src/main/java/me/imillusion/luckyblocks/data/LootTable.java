package me.imillusion.luckyblocks.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LootTable<T> {

    private Random rnd = new Random();

    private Map<T, Integer> objects = new HashMap<>();
    private int total = 0;

    public LootTable<T> register(T object, int weight)
    {
        total += weight;
        objects.put(object, total);
        return this;
    }

    public LootTable<T> register(LootTable<T> table)
    {
        for (T object : table.objects.keySet()) {
            objects.put(object, total + table.objects.get(object));
        }

        total += table.total;
        return this;
    }

    public T getRandom()
    {
        int bound = rnd.nextInt(total);

        for (int i = bound; i <= total; i++)
            if (objects.containsValue(i))
                return getFromVal(i);

        return getFromVal(total);
    }


    private T getFromVal(int val)
    {
        return objects.keySet().stream().filter(t -> objects.get(t) == val).findFirst().get();
    }

}
