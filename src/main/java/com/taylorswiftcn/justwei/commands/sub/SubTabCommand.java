package com.taylorswiftcn.justwei.commands.sub;

import java.util.List;

public abstract class SubTabCommand extends SubCommand {

    public abstract int getLayer();

    public abstract List<String> getTabKeys(int index);
}
