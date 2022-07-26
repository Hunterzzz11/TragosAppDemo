package tesis.hunterzzz.tragoapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tesis.hunterzzz.tragoapp.data.DataSourseImpl
import tesis.hunterzzz.tragoapp.domain.DataSourse
import tesis.hunterzzz.tragoapp.domain.Repo
import tesis.hunterzzz.tragoapp.domain.RepoImpl

/**
 * Created by Jose Luis Quispe on 26/07/2022.
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoimpl:RepoImpl):Repo

    @Binds
    abstract fun dataSourceImpl(datasourceImpl:DataSourseImpl):DataSourse
}