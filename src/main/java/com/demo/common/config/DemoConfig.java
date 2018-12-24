package com.demo.common.config;

import com.alibaba.druid.wall.WallFilter;
import com.demo.blog.BlogController;

import com.demo.common.model._MappingKit;
import com.demo.index.IndexController;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("a_little_config.txt");
        me.setViewType(ViewType.FREE_MARKER);
        me.setDevMode(PropKit.getBoolean("devMode", false));
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", IndexController.class, "/index");    // 第三个参数为该Controller的视图存放路径
        me.add("/blog", BlogController.class);            // 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"

    }

    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {

        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        // StatFilter提供JDBC层的统计信息
        // druidPlugin.addFilter(new StatFi1lter());
        // WallFilter的功能是防御SQL注入攻击
        WallFilter wallDefault = new WallFilter();
        druidPlugin.addFilter(wallDefault);
        druidPlugin.setInitialSize(1);
        druidPlugin.setMinIdle(1);
        druidPlugin.setMaxActive(10);
        me.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(true);
        me.add(arp);


        _MappingKit.mapping(arp);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {

    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {

    }

    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        JFinal.start("WebRoot", 80, "/", 5);
    }
}
