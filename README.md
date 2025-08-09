### 应用名称

SodiumApp


# #### 包结构
1. data 层
   data/: 负责数据层处理
   repository/: 仓库实现，协调本地和远程数据源
   local/: 本地数据源（数据库、SharedPreferences等）
   remote/: 远程数据源（API接口、网络请求等）
   model/: 数据模型类
2. domain 层
   domain/: 业务逻辑层
   usecase/: 业务用例，封装具体业务逻辑
   model/: 领域模型，与UI无关的业务实体
3. ui 层
   ui/: 用户界面相关代码
   screen/: 各个页面的 Composable 函数
   component/: 可复用的 UI 组件
   theme/: 应用主题、颜色、字体等样式定义
   navigation/: 导航相关代码
4. 其他层
   di/: 依赖注入相关代码
   util/: 工具类和扩展函数
   这种分层结构遵循了关注点分离原则，便于团队协作和代码维护。每层都有明确的职责，降低了模块间的耦合度。


## 间距名称
# space_none: 无间距
# space_micro: 微小间距，用于图标边缘
# space_x_small: 超小间距，用于紧密元素
# space_small: 小间距，用于卡片内元素
# space_medium: 中等间距
# space_default: 默认间距
# space_large: 大间距，用于区块分隔
# space_x_large: 超大间距
# space_huge: 巨大间距
# space_massive: 超巨大间距

# space_custom_x  自定义间距 X :100dp
# space_card: 卡片边距
# space_text: 文字边距
# space_icon: 图标边距
# space_divider: 分割线宽度


# radius_none: 无圆角（直角）
# radius_micro: 微小圆角
# radius_small: 小圆角
# radius_medium: 中等圆角
# radius_large: 大圆角
# radius_x_large: 超大圆角
# radius_xx_large: 特大圆角
# radius_huge: 巨大圆角

# radius_avatar_50 头像圆角
# radius_circle_50 头像圆角
# radius_chip_50 标签圆角
# radius_card_50 卡片圆角
# radius_button_8 按钮圆角
# radius_pill_15 胶囊形状

## 颜色名称规范
# <color name="xxx_primary">#FFFFFF</color> 主要的
# <color name="xxx_secondary">#FFFFFF</color> 次要的
# <color name="xxx_tertiary">#FFFFFF</color> 第三主要的
# <color name="xxx_quaternary">#FFFFFF</color> 第四主要的
# <color name="xxx_quinary">#FFFFFF</color> 第五主要的
# <color name="xxx_senary">#FFFFFF</color> 第六主要的
# <color name="xxx_septenary">#FFFFFF</color> 第七主要的
# <color name="xxx_octonary">#FFFFFF</color> 第八主要的
#  foreground_  适用于字体, SVG图标 tint修改图标颜色
#  background_  适用于背景 