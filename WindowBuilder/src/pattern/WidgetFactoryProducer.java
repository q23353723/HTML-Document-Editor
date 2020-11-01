package pattern;

public class WidgetFactoryProducer {

    public static WidgetFactory getFactory(String systemName){
        switch(systemName) {
            case "Windows": return new GrayStyleWidgetFactory();
            case "Linux": return new PurpleStyleWidgetFactory();
        }

        return null;
    }
}
