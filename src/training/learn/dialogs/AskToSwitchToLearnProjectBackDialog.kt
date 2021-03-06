package training.learn.dialogs

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import training.learn.LearnBundle
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class AskToSwitchToLearnProjectBackDialog(private val learnProject: Project, currentProject: Project) : DialogWrapper(currentProject, true) {

  init {
    title = LearnBundle.message("dialog.askToSwitchToLearnProject.title")
    init()
    setButtonsAlignment(SwingConstants.CENTER)
  }

  override fun createActions(): Array<Action> = arrayOf(okAction)

  override fun createCenterPanel(): JComponent? {
    val panel = JPanel(GridBagLayout())
    val gbc = GridBagConstraints()

    val warningMessage = LearnBundle.message("dialog.askToSwitchToLearnProject.message", learnProject.name)

    gbc.insets = Insets(4, 8, 4, 8)
    gbc.weighty = 1.0
    gbc.weightx = 1.0
    gbc.gridx = 0
    gbc.gridy = 0
    gbc.gridwidth = 2
    gbc.fill = GridBagConstraints.BOTH
    gbc.anchor = GridBagConstraints.WEST
    panel.add(JLabel("<html>${warningMessage}</html>"), gbc)

    return panel
  }


}
