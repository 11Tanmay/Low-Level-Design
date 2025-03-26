abstract class BasePizza {
    public abstract int cost();
}

class FarmHouse extends BasePizza {
    @Override
    public int cost() {
        return 200;
    }
}

class VegDeLight extends BasePizza {
    @Override
    public int cost() {
        return 120;
    }
}

class Margherita extends BasePizza {
    @Override
    public int cost() {
        return 100;
    }
}

abstract class ToppingDecorator extends BasePizza {

}

class ExtraCheese extends ToppingDecorator {
    BasePizza basePizza;

    public ExtraCheese(BasePizza pizza) {
        this.basePizza = pizza;
    }

    @Override
    public int cost() {
        return this.basePizza.cost() + 10;
    }
}

class Mushroom extends ToppingDecorator {
    BasePizza basePizza;

    public Mushroom(BasePizza pizza) {
        this.basePizza = pizza;
    }

    @Override
    public int cost() {
        return this.basePizza.cost() + 15;
    }
}
