package unilities;

import org.aeonbits.owner.Config;

@Config.Sources({"file:environmentConfig/env-${environment}.properties"})
public interface IEnvironment extends Config{
    @Config.Key("App.Url")
    String appUrl();

    @Config.Key("App.User")
    String appUser();

    @Config.Key("App.Pass")
    String appPass();
}
