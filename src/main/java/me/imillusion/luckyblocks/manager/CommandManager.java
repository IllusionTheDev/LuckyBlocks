package me.imillusion.luckyblocks.manager;

import me.imillusion.luckyblocks.data.Command;
import me.imillusion.luckyblocks.data.CommandSenderType;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, CommandSenderType> keywords = new HashMap<>();

    public CommandManager()
    {
        keywords.put("[player]", CommandSenderType.PLAYER);
        keywords.put("[console]", CommandSenderType.CONSOLE);
    }

    Command load(String s)
    {
        CommandSenderType type = CommandSenderType.CONSOLE;

        for (String word : s.split(" ")) {
            if (keywords.containsKey(word.toLowerCase())) {
                type = keywords.get(word);
                s = s.replace(word + " ", "");
                break;
            }
        }

        return new Command(type, s);
    }
}
