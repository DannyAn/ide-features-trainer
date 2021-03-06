package training.ui

import com.intellij.ide.ui.UISettings
import com.intellij.openapi.components.ServiceManager
import com.intellij.ui.JBColor
import com.intellij.util.ui.JBUI
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import javax.swing.Box
import javax.swing.JLabel
import javax.swing.border.Border
import javax.swing.border.EmptyBorder
import kotlin.reflect.KProperty

/**
 * Created by jetbrains on 12/08/16.
 */
class UISettings {

    //GENERAL UI SETTINGS
    val width: Int by lazy { JBUI.scale(350) }

    //MAIN INSETS
    val northInset: Int by lazy { JBUI.scale(16) }
    val westInset: Int by lazy { JBUI.scale(13) }
    val southInset: Int by lazy { JBUI.scale(32) }
    val eastInset: Int by lazy { JBUI.scale(32) }
    val checkWidth: Int by lazy { LearnIcons.checkMarkGray.iconWidth }
    val checkRightIndent: Int by lazy { 5 }
    val leftIndent: Int by lazy { JBUI.scale(17) }

    //GAPS
    val headerGap: Int by lazy { JBUI.scale(2) }
    val moduleGap: Int by lazy { JBUI.scale(20) }
    val progressGap: Int by lazy { JBUI.scale(12) }
    val lessonGap: Int by lazy { JBUI.scale(12) }

    val lessonNameGap: Int by lazy { JBUI.scale(5) }
    val beforeButtonGap: Int by lazy { JBUI.scale(20) }
    val afterButtonGap: Int by lazy { JBUI.scale(44) }
    val afterCaptionGap: Int by lazy { JBUI.scale(12) }
    val rateQuestionGap: Int by lazy { JBUI.scale(16) }
    val groupGap: Int by lazy { JBUI.scale(24) }
    val labelLineGap: Int by lazy { JBUI.scale(12) }
    val moduleNameSeparatorGap: Int by lazy { JBUI.scale(5) }
    val moduleNameLessonsGap: Int by lazy { JBUI.scale(10) }

    //FONTS
    val fontSize: Int by lazy { UISettings.instance.fontSize.ifZero(JBUI.scale(13)) }
    val fontFace: String by lazy { UISettings.instance.fontFace ?: JLabel().font.fontName }
    val moduleNameFont: Font by lazy { Font(fontFace, Font.BOLD, fontSize + 1) }
    val plainFont: Font by lazy { Font(fontFace, Font.PLAIN, fontSize) }
    val italicFont: Font by lazy { plainFont.deriveFont(Font.ITALIC) }
    val boldFont: Font by lazy { plainFont.deriveFont(Font.BOLD) }
    val lessonHeaderFont: Font by lazy { Font(fontFace, Font.BOLD, fontSize + 2) }
    val radioButtonLabelFont: Font by lazy { Font(fontFace, Font.PLAIN, fontSize - 2) }

    //COLORS
    val defaultTextColor = JBColor(Color(30, 30, 30), Color(208, 208, 208))
    val lessonActiveColor = JBColor(Color(0, 0, 0), Color(202, 202, 202))
    val lessonCodeColor = JBColor(Color(27, 78, 128), Color(85, 161, 255))
    val lessonLinkColor = JBColor(Color(17, 96, 166), Color(104, 159, 220))
    val shortcutTextColor = JBColor(Color(12, 12, 12), Color(200, 200, 200))
    val separatorColor = JBColor(Color(204, 204, 204), Color(149, 149, 149))
    val shortcutBackgroundColor = JBColor(Color(218, 226, 237), Color(39, 43, 46))
    val passedColor = JBColor(Color(105, 105, 105), Color(103, 103, 103))
    val backgroundColor = Color(245, 245, 245)
    val descriptionColor = Color(128, 128, 128)
    val radioButtonLabelColor = descriptionColor
    var questionColor = lessonActiveColor

    val emptyBorder: Border
        get() = EmptyBorder(northInset, westInset, southInset, eastInset)

    val checkmarkShiftBorder: Border
        get() = EmptyBorder(0, checkIndent, 0, 0)

    val checkIndent: Int
        get() = checkWidth + checkRightIndent

    companion object {
        val instance: training.ui.UISettings
            get() = ServiceManager.getService(training.ui.UISettings::class.java)

        fun rigidGap(gapName: String, gapSize: Int, isVertical: Boolean = true): Box.Filler {
            val rigidArea = if (isVertical) Box.createRigidArea(Dimension(0, gapSize)) else Box.createRigidArea(Dimension(gapSize, 0))
            rigidArea.name = gapName
            return rigidArea as Box.Filler
        }

        fun rigidGap(settingsValue: KProperty<Int>, isVertical: Boolean = true): Box.Filler {
            val value = settingsValue.call(training.ui.UISettings.instance)
            return rigidGap(settingsValue.name, value, isVertical)
        }
    }

    private fun Int.ifZero(nonZeroValue: Int): Int =
        if (this == 0) nonZeroValue else this
}

